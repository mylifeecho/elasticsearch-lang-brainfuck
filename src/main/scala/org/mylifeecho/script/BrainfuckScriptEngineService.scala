package org.mylifeecho.script

import java.util

import org.elasticsearch.common.component.AbstractComponent
import org.elasticsearch.common.inject.Inject
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.script._
import org.elasticsearch.search.lookup.SearchLookup

class BrainfuckScriptEngineService @Inject() (settings: Settings) extends AbstractComponent(settings) with ScriptEngineService {

  override def types(): Array[String] = Array("bf", "brainfuck")

  override def extensions(): Array[String] = Array("brainfuck")

  override def compile(script: String): AnyRef = script

  override def scriptRemoved(script: CompiledScript): Unit = {}

  override def execute(compiledScript: scala.Any, vars: util.Map[String, AnyRef]): AnyRef = {
    BrainfuckEval.eval(compiledScript.asInstanceOf[String].toCharArray)
  }

  override def unwrap(value: AnyRef): AnyRef = value

  override def sandboxed(): Boolean = false

  override def close(): Unit = {}

  override def executable(compiledScript: scala.Any, vars: util.Map[String, AnyRef]): ExecutableScript = {
    new BrainfuckExecutableSearchScript(compiledScript.asInstanceOf[String])
  }

  override def search(compiledScript: scala.Any, lookup: SearchLookup, vars: util.Map[String, AnyRef]): SearchScript = {
    new BrainfuckExecutableSearchScript(compiledScript.asInstanceOf[String])
  }
}