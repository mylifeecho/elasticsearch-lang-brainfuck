package org.mylifeecho.script

object BrainfuckEval {
  def eval(bf : Array[Char]) = {
    var memory : Array[Integer] = Array.fill[Integer](30000)(0)
    var memptr : Integer = 0
    var prgptr : Integer = 0
    var output = ""

    def jump( dir : Int ) = {
      var loop = 1
      while( loop > 0 ) {
        prgptr += dir
        loop += (bf( prgptr ) match {
          case '[' => dir
          case ']' => -dir
          case _ => 0
        })
      }
    }

    while( prgptr < bf.size ) {
      bf(prgptr) match {
        case '>' => memptr += 1
        case '<' => memptr -= 1
        case '+' => memory(memptr) += 1
        case '-' => memory(memptr) -= 1
        case '.' => output = output.concat(memory(memptr).toChar.toString)
        case ',' => memory(memptr) = readInt()
        case '[' => if( memory( memptr ) == 0 ) jump( 1 )
        case ']' => if( memory( memptr) != 0 ) jump( -1 )
      }
      prgptr += 1
    }
    output
  }
}
