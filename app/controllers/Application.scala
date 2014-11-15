package controllers

import models.ChatRoom
import play.api.libs.json.JsValue
import play.api.mvc._
import scala.concurrent.ExecutionContext

object Application extends Controller {
  
  /**
   * Just display the home page.
   */
  def index = Action { implicit request =>
    Ok(views.html.index())
  }
  
  /**
   * Display the chat room page.
   */
  def chatRoom(username: Option[String]) = Action { implicit request =>
    username.filterNot(_.isEmpty).map { username =>
      Ok(views.html.chatRoom(username))
    }.getOrElse {
      Redirect(routes.Application.index).flashing(
        "error" -> "Please choose a valid username."
      )
    }
  }

  def chatRoomJs(username: String) = Action { implicit request =>
    Ok(views.js.chatRoom(username))
  }
  
  /**
   * Handles the chat websocket.
   */
  def chat(username: String) = WebSocket.tryAccept[JsValue] { request  =>
    ChatRoom.join(username).map(Right.apply)(ExecutionContext.global)
  }

}
