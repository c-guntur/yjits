record Speakers(String speaker1, String speaker2) { }

String year = Year.now().toString();

void main() {
	println("Hello JavaOne " + year);
	println(new Speakers("Mala", "Chandra"));
	println("😀😀😀😀😀");
}

// Record instead of a data class

// Instance main()
// Unnamed class, implicit class

// Class variables don't need to be static to be accessed in main()

// Use IO.println with import
// Static methods in java.io.IO are automatic imports

// import module - default import module java.base
// No need to import default available modules

// Terminal => java --enable-preview HelloJavaOne.java









