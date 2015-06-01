package org.mylifeecho.script

import java.util

import org.apache.lucene.index.AtomicReaderContext
import org.apache.lucene.search.Scorer
import org.elasticsearch.script.{ExecutableScript, SearchScript}

class BrainfuckExecutableSearchScript(script: String) extends ExecutableScript with SearchScript {
  override def unwrap(value: scala.Any): AnyRef = Nil

  override def setNextVar(name: String, value: scala.Any): Unit = {}

  override def run(): AnyRef = BrainfuckEval.eval(script.toCharArray)

  override def runAsFloat(): Float = run().asInstanceOf[Float]

  override def setNextDocId(doc: Int): Unit = {}

  override def setNextSource(source: util.Map[String, AnyRef]): Unit = {}

  override def runAsLong(): Long = run().asInstanceOf[Long]

  override def runAsDouble(): Double = run().asInstanceOf[Double]

  override def setScorer(scorer: Scorer): Unit = {}

  override def setNextReader(reader: AtomicReaderContext): Unit = {}
}