val l = List(1 to 10)
//zad1
def isSorted(l:List[Int]): Boolean = l match {
case p :: r :: ss => 
	if (p < r){						// można zrobić (p<r) && isSorted(r:: ss), bez klameerek
			isSorted(r :: ss)
		}else{
			false
		}
case _ => true 
	}
//zad2 (????)
def isSortedHO(l:List[Int], isOrdered:(Int,Int)=>Boolean) :Boolean = {
	l match {
		case p :: r :: ss => isOrdered(p,r) && isSortedHO(l.tail, isOrdered)
		case _ => true 
			}
}
//zad3
type Set = Int => Boolean

val s1 : Set = (x: Int) => {(x > 4) && (x < 10)}
val s2 : Set = (x: Int) => (x < 8)

def dodaj (s:Set, t:Set): Set = {
	(x:Int) => {s(x) || t(x)}
}

val s3 = dodaj(s1,s2)

//zad4 _ mastermind oceń jakość strzału!
// ile było strzałów 100% celnych a ile strzałów występujących rozlokowanych
//cel [1,2,3,1,2,2]
//strzał [1,1,2,2,4,4]
type mSet = Int => Int

def ocena(cel: List[Int], strzal: List[Int]): (Int, Int) = {
	cel match {

	}
}