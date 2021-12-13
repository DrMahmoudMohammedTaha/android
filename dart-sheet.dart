
// String interpolation
// Every language has it's own way of interpolating 
// two ore more words or characters. 
// In dart, you can put value of an expression inside a string as follows:

int x=6;
int y=2;
String sum = '${x+y}';          // result is 8
String subtraction = '${x-y}';  // result is 4
String upperCase = '${"hello".toUpperCase()}'; // result is HELLO
String concatXY = '$x$y'; // result is '62'

// Functions
// Dart lang is an OOL(Object-oriented language), 
// In this language, functions are objects and have a type, Function. 
// This implies that functions can be assigned to variables or 
// passed as args to other functions. 
// Interestingly, you can also call an instance of a class as 
// if it were a fuction. That's awesome, right?
String fullName(){
    String firstName = "Temidayo";
    String lastName = "Adefioye";
    return '$firstName $lastName'; // returns 'Temidayo Adefioye'
}
int length(String text){
    return text.length; // returns length of text
}

// The above function can be rewritten in a more concise way:
// This approach is applicable where functions contain just ONE expression. 
int length(String text) => return text.length; // returns length of text

// Parsing
var a = "121";
var b = "120.56";
var c = "100.a12";         
var d = "abc";
String parseA = int.tryParse(a); // result is 121
String parseB = double.tryParse(b); // result is 120.56
String parseC = double.tryParse(c); // result is null (that string contains invalid number)
String parseD = double.tryParse(d); // result is null (that string contains invalid number)

// List Arrays
var numList = [1,2,3,5,6,7];
var countryList = ["Nigeria","United States","United Kingdom","Ghana","IreLand","Germany"];
String numLength = numList.length; // result is 6
String countryLength = countryList.length; // result is 6
String countryIndex = countryList[1]; // result is 'United States'
String numIndex = numList[0]; // result is 1
countryList.add("Poland"); // Adds a new item to the list.
var emailList = new List(3); // Set a fixed list size 
var emailList = new List<String>(); // instance of a list of type String

// Lambda Functions
var numList = new List<int>.generate(5,(i) => i);
print(numList); //result: {0,1,2,3,4}
var loans = numList.map( (n) => "\#$n").toList();
print(loans); // result: {#0, #1, #3, #4}
printMsg()=> print("Hello world");

// You can declare a state function this way in flutter
_DashboardState createState() => _DashboardState(); 

// How about creating a widget using lambda?
Card makeCard(Asset assetViewModel) => Card(
      elevation: 8.0,
      margin: new EdgeInsets.symmetric(horizontal: 10.0, vertical: 6.0),
      child: Container(
        decoration: BoxDecoration(color: Colors.white),
        child: makeListTile(assetViewModel), // where makeListTile is a custom widget created already
      ),
);

// Null-aware Operators
// Handling null exceptions in app development is very essential, 
// as this allows you to create a seamless experience 
// for your app users. 
// One is the ??= assignment operator, which assigns a value of a variable only 
// if that variable is currently null:
int x; // The initial value of any object is null
x ??=6;
print(x); // result: 6
x ??=3;
print(x); // result is still 6
print(null ?? 10); // result: 10. 

// Conditional Property Access
// To properly safegaurd access to a property or method of an object that might be null, 
// put a question mark (?) before the (.)
userObject?.userName
//The code snippet above is equivalent to following:
(userObject !=null) ? userObject.userName : null
//You can chain multiple uses of ?. together in a single expression
userObject?.userName?.toString()

Collections Literals
final fruitList = ["Orange","Bannana","Carrot","Apple"]; // A list of fruit
final countrySet = {"Nigeria","United States","Poland","Italy"}; // A set of country
final credMap = {
   'userName': 'temi',
   'password': 'xmen'
} // A map of user credentials

// You maybe wondering why we didn't explicitly declare a type for all 
// of the collections above. It's interesting to know that dart's type inference 
// can assign types to these variables for you. In this case, the inferred types are List,
final fruitList = <String>[];
final countrySet = <String>{};
final credMap = <String, String>{};

Arrow Syntax
String _firstName = "Michael";
String _lastName = "Jones";
String _middleName = "Will";
String get fullName => '$_firstName $_middleName $_lastName'; // result: 'Michael Will Jones'

// Iterations
for (int i=0; i<=20; i++){
   print(i); // prints 1 to 20
}

var fruitList = ["Orange","Bannana","Carrot","Apple"];
for (final fruit in fruits){
   print(fruit); // prints all types of fruit in the list
}

// Map
var user = new Map();
// To initialize the map, do this:
user['firstName'] = 'Paul';
user['lastName'] = 'Pogba';
// Result: {firstName: Paul, lastName: Pogba}


// Below are map properties
- Keys
- Values
- Length
- isEmpty
- isNotEmpty

// Below are map functions
- addAll()
- clear()
- remove()
- forEach()

// Variables
int x = 2; // explicitly typed
var p = 5; // type inferred
p = "cool"; // ops! throws an error
dynamic z = 8; // variable can take on any type
z = "cool"; // cool

// Class
class Car {  
  // field 
  String engine = "E1001"; 
  // function 
  void disp() { 
     print(engine); 
  } 
}

// Getters and Setters
class Person {
  String firstName;
  String lastName;
  double height;
  int personAge;
  int yearofBirth;
  double weight;

  int get age {
    return personAge;
  }

  void set age(int currentYear) {
    personAge = currentYear - yearofBirth;
  }

  Person({this.firstName,this.lastName,this.personAge,this.yearofBirth,this.weight});
}

void main() {
 Person person = Person(firstName:"Thanos",lastName:"Rednos",yearofBirth:1990,weight:200.5);
  print(person.firstName); // output - Thanos
  print(person.lastName); // output - Rednos
  person.age = 2019;
  print(person.age); // output - 29

}

// Futures: Async and Await
// The async and await keywords provide a declarative way 
// to define asynchronous functions and use their results. 
// To define an async function, add async before the function body:
// The await keyword works only in async functions.
Future<String> login() {
 // Imagine that this function is
 // more complex and slow.
 String userName="Temidjoy";
 return
  Future.delayed(
    Duration(seconds: 4), () => userName);
}
// Asynchronous
main() async {
 print('Authenticating please wait...');
 print(await userName());
}

// JSON and serialization
// dart:convert Converters for both JSON and UTF-8 
// package:json_serializable An easy-to-use code generation package. 
// When you add some metadata annotations and use the builder 
// provided by this package, the Dart build system generates 
// serialization and deserialization code for you.
import 'dart:convert';
import 'dart:io';
Future<void> main(List<String> args) async {
 var file = File(args[0]);
 var lines = utf8.decoder
     .bind(file.openRead())
     .transform(LineSplitter());
 await for (var line in lines) {
   if (!line.startsWith('#')) print(line);
 }
}