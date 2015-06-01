package org.mylifeecho

import org.elasticsearch.plugins.AbstractPlugin
import org.elasticsearch.script.ScriptModule
import org.mylifeecho.script.BrainfuckScriptEngineService

class BrainfuckPlugin extends AbstractPlugin {
  override def name(): String = "lang-brainfuck"

  override def description(): String = "Adds support for writing scripts in Brainfuck"

  def onModule(module: ScriptModule): Unit = module.addScriptEngine(classOf[BrainfuckScriptEngineService])
}