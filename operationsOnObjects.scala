val l = List(1,2,3,4,5,6)
val l2 = List(1,2,2,3,3,3,4,1,3,5,6,6,6)

//zad1
def rev[T](l: List[T]): List[T] = l match{
	case head :: tail => rev(tail) ++ List(head)
	case _ => List()
}

//zad2
def compress[T](l: List[T], acc: List[T] = List()): List[T] = l match{
	case head :: tail => {
		if(acc.length == 0) compress (tail, acc++List(head))
		else{
			if(acc.last == head) compress (tail, acc)
			else compress (tail, acc++List(head))
		}
	}
	case _ => acc
}

//zad3
def multi[T](a: T, n: Int, l: List[T] = List()): List[T] = {
		if(n != 0){
			multi(a, n-1, l ++ List(a))
		}
		else{
			l
		}
}

def multimply[T](l: List[T], n: Int, acc: List[T] = List()): List[T] = l match{
	case head :: tail => multimply(tail,n, acc++multi(head, n))
	case _ => acc
}

//zad4
def slice[T](l: List[T], b: Int, e: Int): List[T] = {
	if(b>e)
		Nil
	else
		l.take(e).drop(b-1)
}

//zad5
def insert[T](l: List[T], el: T, pos: Int): List[T] = {
	l.take(pos-1)++List(el)++l.drop(pos-1)
}

//zad6
def mapAdd[T](m: Map[T, Int], x: (T, Int)): Map[T, Int] = x match{
      case (v1, v2) => m+(v1 -> (m.getOrElse(v1,0) + v2))
      case _ => m
}

def freq[T](l: List[T]): Map[T, Int] = {
	l.map((a: T) => (a, 1)).foldLeft(Map[T, Int]())((acc: Map[T, Int], a: (T, Int)) => mapAdd(acc, a))
}

//zad7
def contain[T](l: List[T], s: List[T]): Boolean = s match{
	case head :: tail => if(l.contains(head)) contain(l,tail) else false
	case _ => true
}

def isPerm[T](l: List[T], s: Set[T]): Boolean = {
	if(l.length == s.toList.length)
		contain(l,s.toList)
	else
		false
}