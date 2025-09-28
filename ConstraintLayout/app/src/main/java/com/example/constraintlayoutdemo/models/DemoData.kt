package com.example.constraintlayoutdemo.models

object DemoData {

    fun getPersonList() : List<Person> {
        val personList : MutableList<Person> = mutableListOf()
        personList.add(Person("Wolfgang Ernst Pauli", "sample_image_1", true))
        personList.add(Person("Wilhelm Conrad Roentgen", "sample_image_2", false))
        personList.add(Person("Werner Karl Heisenberg", "sample_image_3", true))
        personList.add(Person("Stephen Hawking", "sample_image_4", true))
        personList.add(Person("Sir Isaac Newton", "sample_image_5", false))
        personList.add(Person("Sir Ernest Rutherford", "sample_image_6", false))
        personList.add(Person("Shannon W. Lucid", "sample_image_7", true))
        personList.add(Person("Sarah Boysen", "sample_image_8", false))
        personList.add(Person("Ruzena Bajcsy", "sample_image_9", true))
        personList.add(Person("Rosalind Franklin", "sample_image_10", true))
        personList.add(Person("Rita Levi-Montalcini", "sample_image_11", false))
        personList.add(Person("Richard Phillips Feynman", "sample_image_12", true))
        personList.add(Person("Polly Matzinger", "sample_image_13", false))
        return personList
    }
}