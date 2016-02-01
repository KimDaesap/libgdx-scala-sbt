package com.daesap.gdx.launcher

import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}
import com.daesap.gdx.core.GameCore

object DesktopLauncher {

  def main(args: Array[String]): Unit = {
    val config: LwjglApplicationConfiguration = new LwjglApplicationConfiguration
    new LwjglApplication(new GameCore, config)
  }

}
