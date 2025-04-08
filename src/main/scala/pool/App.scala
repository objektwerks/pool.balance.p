package pool

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging

import scalafx.application.JFXApp3

object App extends JFXApp3 with LazyLogging:
  logger.info("Starting app ...")

  val context = Context( ConfigFactory.load("app.conf") )
  val view = View(context)

  override def start(): Unit =   
    stage = new JFXApp3.PrimaryStage:
      scene = view.scene
      title = context.windowTitle
      minWidth = context.windowWidth
      minHeight = context.windowHeight
      icons += context.logo
