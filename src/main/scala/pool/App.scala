package pool

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging

import scalafx.application.JFXApp3

object App extends JFXApp3 with LazyLogging:
  override def start(): Unit =
    val context = Context( ConfigFactory.load("app.conf") )
    val view = View(context)
    
    stage = new JFXApp3.PrimaryStage:
      scene = view.scene
      title = context.windowTitle
      minWidth = context.windowWidth
      minHeight = context.windowHeight
      icons.add(context.logo)

    logger.info("App started.")

  override def stopApp(): Unit = logger.info("App stopped.")