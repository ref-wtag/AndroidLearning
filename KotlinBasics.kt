package com.example.testexample
import org.w3c.dom.ls.LSException
import java.lang.reflect.Array.get

fun main() {
    //kotlin computed var
    //testComputedVar()

    //kotlin higher order functions
    //testHigherOrderFunction()

    //assign function to a variable
    //testAssignFunctionToVariable()

    //kotlin lambda
    //testLambda()

    //kotlin foreach
    //testForEach()

    //kotlin map
    //testMap()

     //- kotlin filter
    //testFilter()

    //---extension function
    //testExtension()

    //---inline function
    //testInlineFunction()

    // --- scope functions---
    // testScopeFunction()

    // ---kotlin generics---
    //testGeneric()

    //---varArgs----
    //testVarArgs()

    //---nested class
    //testNestedClass()

    //---inner class
   // testInnerClass()
}

fun testComputedVar() {
    println(computedVar)
    computedVar = "new string"
    println(computedVar)

    println(simplifiedComputedVar)
    simplifiedComputedVar = "hello new computed var"
    println(simplifiedComputedVar)
}

var computedVar: String = ""
    get() = "hello there"
    set(value) {
        //computedVar = value // this will not work
        field = value
    }

var simplifiedComputedVar: String = ""
    get() = "hello computed var"

fun testHigherOrderFunction() {
  val res = calculator(2, 5, ::sumInt)

    println(res)
}

fun testAssignFunctionToVariable() {
    var fn: (a: Double, b: Double) -> Double = ::sumDouble
    println(fn(2.0, 5.0))
}

fun sumDouble(a:Double, b: Double) : Double {
    return a+b
}

fun sumInt(a:Int, b:Int) : Int {
    return a+b
}

fun testLambda() {
    val lambda1 = { a:Int, b:Int -> a+b }
    val multiLineLambda = {
        println("hello lambda")
        val a = 2+3
        "Hello World"//; last line is the return type
    }

    val anotherLambda = { a:Int, b:Int ->
        val const = 10
        const
    }

    var res = lambda1(2, 4)
    println(res)
    val str = multiLineLambda()
    println(str)
    res = anotherLambda(5, 7)
    println(res)

    val singleParamLambda = {x: Int -> x * x }
    val lambda2 :(Int) -> Int = { a -> a+a }

    val sayHi = { msg: String -> println("say hi!") }
    val sayHi2: (String) -> Unit = { msg -> println("say hi2") }

    val singleParam : (Int) -> Int = { a -> a+a }
    val simplifySingleParam: (Int) -> Int = { it*it }

    res = calculator(1, 2) { a, b -> a + b }
   println(res)
}

fun calculator(a:Int, b:Int, op:(Int, Int) -> Int) : Int {
  return op(a, b)
}

fun testForEach() {
    var nums = listOf(1, 2, 3)

    nums.forEach {
        println(it)
    }
}

fun testMap() {
    val num = listOf(1, 2, 3)
    val list = num.map { it*it }
    println(list)

    val employeeList = listOf(
        Employee("1", "name1"),
        Employee("2", "name2")
    )

    val newEmployeeList = employeeList.map {
        NewEmployee(it.id, it.name)
    }
    println(newEmployeeList)
}

fun testFilter() {
   val nums = listOf(1, 2, 3, 4)
    println(isOdd(3))

    val list = nums.filter(::isOdd)

    val list2 = nums.filter { it ->
        it%2 !=0
    }

    println(list)
    println(list2)

    val employeeList = listOf(
        Employee(id = "1", name = "refat1"),
        Employee(id = "2", name = "refat2"),
        Employee(id= "2", name = "refat3")
    )

    val empList = employeeList.filter {
       it.id == "2"
    }
    println(empList)
}

fun isOdd(value: Int): Boolean {
    return value%2 == 0
}

fun testInlineFunction() {
    calculateTime {
        loop()
    }
}

inline fun calculateTime(fn: () -> Unit ) {
    val start = System.currentTimeMillis()
    fn()
    val end = System.currentTimeMillis()
    println("Time taken ${end - start} ms")
}

fun loop() {
    for(i in 1..10000000) {
  // do nothing
    }
}

fun testExtension() {
  println("Hello from test extension".extension())
    val obj = Testing()
    val c = obj.c()

    println(c)
}

fun String.extension(): String {
    return "---$this---"
}

class Testing() {
    fun a() = "Hello A"
    fun b() = "Hello B"
}

fun Testing.c(): String {
   return "Hello C"
}

fun testScopeFunction() {
    testApply()
    testLet()
    testWith()
    testRun()
}

fun testApply() {
    val employee = Employee()
    employee.name = "Refat1"
    employee.id = "1111"

    println(employee)

    employee.apply {
        name = "Refat2"
        id = "2222"
    }
    println(employee)
}

fun testLet() {
    val employee: Employee? = Employee()

    val x =
        employee?.let {
            it.name = "refat"
            it.id = "222"
            "2"
        }
    println(employee)
    println(x)
}

fun testWith() {
    val employee = Employee()

    val x =
        with(employee) {
            name = "refat"
            id = "222"
            "returning from here"
        }
    println(employee)
    println(x)
}

fun testRun() {
    val employee = Employee()

    employee.run {
        name = "refat"
        id = "111"
    }

    println(employee)
}

data class Employee(
    var id: String = "12345",
    var name: String = "Refat",
)
data class NewEmployee(
    var id: String,
    var name: String
)


fun testGeneric() {
    val firstObj = GenericClass(3)
    val firstValue = firstObj.getValue()

    val secondObj = GenericClass("hello world")
    val secondValue = secondObj.getValue()

    println(firstValue)
    print(secondValue)
}

class GenericClass<T>(
    var data: T,
) {
    fun setValue(value: T) {
        data = value
    }

    fun getValue(): T = data
}

fun testVarArgs() {
    varArgs(1,1,3)
    varArgs(5, 5, 5)
}

fun varArgs(vararg data: Int) {
  var sum = 0
    for(i in data) {
      sum += i
    }
    println(sum)
}

fun testNestedClass() {
   val obj = OuterClass()
    obj.data = 10

    val obj2 = OuterClass.NestedClass()
    obj2.testNested()
}

class OuterClass {
    var data = 5

    class NestedClass() {
        fun testNested() {
            println("i am in nested class")
        }
    }
}

fun testInnerClass() {
    val obj = OuterClassNew()
    obj.data = 10

    val obj2 = OuterClassNew().NestedClass()
    obj2.testInner()
}

class OuterClassNew {
    var data = 5

    inner class NestedClass() {
        fun testInner() {
            println("i am in inner class")
            println(data)
        }
    }
}
