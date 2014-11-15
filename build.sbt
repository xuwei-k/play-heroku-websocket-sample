name := "play-websocket"

scalaVersion := "2.11.4"

enablePlugins(PlayScala)

enablePlugins(HerokuPlugin)

herokuAppName in Compile := "play-websocket"

herokuJdkVersion in Compile := "1.8"

scalacOptions ++= (
  "-deprecation" ::
  "-feature" ::
  "-unchecked" ::
  "-language:existentials" ::
  "-language:higherKinds" ::
  "-language:implicitConversions" ::
  Nil
)
