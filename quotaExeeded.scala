def odwrocListe[T](l:List[T]): List[T] = {
    l match {
      case h :: t => odwrocListe(t) ::: List(h)
      
      case _ => List()
    }
  }  

def compress[T](l:List[T]): List[T] = {

    comp(l,Nil, List)
}  

def comp[T](l:List[T] , p:T, w:List[T]): List[T] = {
		l match {
		case h :: t => {
			if (p == Nil) comp(t, h, w)
			else
				if (p == h) comp(t, h , w)
				else comp(t,h,w::h)
		}
	}	
}

def multiply[T](l:List[T], n:Int): List[T] = {
l match {
      case h :: t => {
      	m(h,n) ::: multiply(t,n)
      }
      
      case _ => Nil
    }
  }  

def m[T](e:T, n:Int): List[T] = {
	if(n > 0) {
		e :: m(e, n-1)
	}
	else
		Nil
}

def slice[T](l:List[T], b:Int, e: Int): List[T] = {
	if (b <= e)
			l(b-1) :: slice(l,b+1,e)
		else
			Nil
} 

def insert[T](l:List[T], e: T, i: Int): List[T] = {

	slice(l,1,i-1) ::: List(e) ::: slice(l,i, l.length)
} 

def freq[T](l:List[T]): Map[T, Int] = {

	 fr(l, Map[T, Int]())     
    
} 

def fr[T](l:List[T], m:Map[T, Int]): Map[T, Int] = {

	l match {
		case h :: t => {
			val tmp = m.getOrElse(h, 0)
			fr(t, m.updated(h,tmp+1))
		}
		case _ => m
	}	
} 

def isPerm[T](l:List[T], s:Set[T]): Boolean = {
	val sl = s.toList().sorted;
	val ll = l.sorted;
	if(ll.length == sl.length)
		for(i <- 0 to sl.length)
		{
			if(ll(i) != sl(i))
				false
		}
		true
}

def srt(l:List[(String,Int)]): List[(String,Int)] = {

l match {
      case h :: t => odwrocListe(t) ::: List(h)
      
      case _ => List()
    }
}