#Exquisite

Semesterarbeit Formale Sprachen

FFHS - FAC BE-Sa-1

Jan Capasso


---
#Inhalt
Exquisite ist eine einfache Skript-Sprache, bei der darauf geachtet wurde, dass die Befehle möglichst lesbar sind.
Das Projekt umfasst einen Scanner (jFlex) sowie einen Parser (CUP). In der Abgabe sind nur 
folgende Kern-Funktionen umgesetzt worden:
- Definieren von Variablen
- Arithmetische Ausdrücke
- Bedingte If-Else Anweisungen
- Definieren und Aufrufen von Funktionen
- Ausführen von while-Schlaufen
- Konsolen ausgaben mit einsetzen von Variablen

---

#Installation
Das Projekt wurde mit maven erstellt. Im Import von modernen IDE's sollten die Bibliotheken automatisch hinzugefügt werden.

---

#Aufbau Projekt
Scanner File [Scanner](./src/main/jflex/grammer.flex)

Parser File [Parser](./src/main/cup/grammer.cup)

Die [Shell](./src/main/java/Shell.java) ist eine Java Klasse, welche eine direkt ausführbare Möglichkeit für die Skript-Sprache bietet.

Das Hauptprojekt befindet sich unter  [Projekt](./src/main/java) und ist unterteilt in:
- Im [core](./src/main/java/core) finden sich Sprachen-Spezifische Exceptions und ein VoidObjekt, dass bei keiner Rückgabe 
anstatt ``null`` zurückgegeben wird.
- In [enumerations](./src/main/java/enumeration) befinden sich alle Enums.
- In [instruction](./src/main/java/instruction) befindet sich die Zuweisung zu Java-Klassen der Sprache. Die Umsetzung wurde mit dem Visitor Pattern realisiert. Der [Evaluator](./src/main/java/Evaluator.java) beinhaltet die ausimplementierung der Logik.

- Die Junit-Tests zur Sprache befinden sich unter
[Tests](./src/test)

---
# Vergleich Java zu Exquisite
##Java
    static void main(List<String> args) {
        int max = 100;
        hugo(max);
    }

    void hugo(int max) {
        int count = 1;
        while (count <= max) {
            if (count % 7 == 0) {
                System.out.prinln("Hugo");
            } else {
                System.out.prinln(count);
            }
        }
    }

##Excuisite

    set 100 as max
    execute hugo with max

    task hugo takes max [
    set 1 as count
    as long as count lower or equal  max [
    in case that count modulo 7 equal to 0[
                                            ...
                                            ]
    fallback [
            ...
            ]
        ]
      ]
    ]
---
#Beispiele
#Vergleich Operatoren
    x equal y
    x not equal y
    x lower y
    x greater y
    x lower or equal y
    x not equal y
##Mathematische Operatoren
    x add y
    x substract y
    x multiply y
    x divide y
##Logische Operatoren
    x equal y and a equal b
    x equal y or a equal b
    x equal y not and a equal b x equl y not or a equal b not x equal y
##Zuweisen von Variablen
    set 1 as var 

    set 1 add 2 as var

    set 1 add var as otherVar

##Bedingungen
Die Rückgabe der Funktionen ist jeweils die letzte Ausführung.
###If Block
    in case that x equal y [ set 1 as var ]
###If-else Block
    in case that x equal y [set 1 as var] fallback [ set 2 as var ]

##Funktion definieren
    task square takes x,y [ set x add y as result ]
###Funktion definieren und ausführen
    set 2 as var
    task square takes x [ set x multiply x as result ] execute square with var;
##Schlaufen
    as long as amount equal 2 [
        set amount add 0.1 as amount
        set result add 5 as result
        ]

##Ausgabe in Konsole
    print('Hello World')

###Ausgabe in Konsole mit Variablen
    set 2 as value
    print('Value $value')
