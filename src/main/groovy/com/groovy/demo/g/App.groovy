package com.groovy.demo.g

import com.groovy.demo.Ad
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.text.SimpleTemplateEngine
import groovy.text.markup.MarkupTemplateEngine

class App {

    static void main(String[] args) {
        println "sdfdsf"
        def Ad a = new Ad()
        a.setA("a")
        a.setB("b")
        a.setC("c")
        JsonOutput jsonOutput = new JsonOutput()
        println jsonOutput.toJson("sdfsdf")
        println jsonOutput.toJson(a)

        def templateStr = '''
        xmlDeclaration()
        cars {
            cars.each {
                car(make: it.make, model: it.model)
            }
        }
        '''
        def engine = new MarkupTemplateEngine()
        def model = [cars: [new Car(make: 'Peugeot', model: '508'), new Car(make: 'Toyota', model: 'Prius')]]
        def model111 = [cars: new Car(make: 'Peugeot', model: '508')]
        def template = engine.createTemplate(templateStr)
        def result = template.make(model)
        println result

        jsonConvert()


        //----------------------------
        JsonSlurper jsonSlurper = new JsonSlurper()
        def o = jsonSlurper.parseText(jsonOutput.toJson(model))
        //----------------------------


        Target target = new Target()
        target.name = o.cars[0].make
        def aa = model["cars"].stream().collect {
            new Target(name: it?["make"], brand: it?["model"])
        }
        def result1 = outputJson(aa)
        println result1

        def aa2 = model?.cars?.stream().collect {
            new Target(name: it.make, brand: it.model)
        }
        def result2 = outputJson(aa2)
        println "222222   "+result2

        def aa3 = o?.cars?.stream().collect {
            new Target(name: it.make, brand: it.model)
        }
        def result3 = outputJson(aa3)
        println "333333   "+result3
    }


    static String jsonConvert() {
        def templateStr = '''

[
            <%
                cars.each{
                    //println  "${it.toString()}"
                    println "{\\"厂商\\": \\"${it.make}\\",\\"型号\\": \\"$it.model\\"},"
                }
                
            %>
]
        '''

        def aa = '''
"\\{
                    "厂商": ${make},
                    "型号": ${model}
                \\}'''
        def engine = new SimpleTemplateEngine()
        def model = [cars: [new Car(make: 'Peugeot', model: '508'), new Car(make: 'Toyota', model: 'Prius')]]
        def template = engine.createTemplate(templateStr)
        def result = template.make(model)
        println result
    }

    static String outputJson(Object object) {
        JsonOutput output = new JsonOutput()
        return output.toJson(object)
    }
}
