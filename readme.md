#Exquisite

Semesterarbeit Formale Sprachen

FFHS - FAC BE-Sa-1

Jan Capasso

Einige Beispiele sind zu finden unter [Beispiele.pdf](./Beispiele.pdf)

---
##Inhalt
Exquisite ist eine einfache Skript-Sprache, bei der darauf geachtet wurde, dass die Befehle möglichst lesbar sind.
Das Projekt umfasst einen Scanner (jFlex) sowie einen Parser (CUP). In der Abgabe sind nur 
folgende Kern-Funktionen umgesetzt worden:
- Definieren von Variablen
- Arithmetische Ausdrücke
- Bedingte If-Else Anweisungen
- Definieren und Aufrufen von Funktionen
- Ausführen von while-Schlaufen

##Installation
Das Projekt wurde mit maven erstellt. Im Import von modernen IDE's sollten die Bibliotheken automatisch hinzugefügt werden.

---

##Aufbau Projekt
Scanner File [Scanner](./src/main/jflex/grammer.flex)
Parser File [Parser](./src/main/cup/grammer.cup)

Das Hauptprojekt befindet sich unter  [Projekt](./src/main/java) und ist unterteilt in:
- Im [core](./src/main/java/core) finden sich Sprachen-Spezifische Exceptions und ein VoidObjekt, dass bei keiner Rückgabe 
anstatt ``null`` zurückgegeben wird.
- In [enumerations](./src/main/java/enumeration) befinden sich alle Enums.
- In [instruction](./src/main/java/instruction) befindet sich die Zuweisung zu Java-Klassen der Sprache. Die Umsetzung wurde mit dem Visitor Pattern realisiert. Der [Evaluator](./src/main/java/Evaluator.java) beinhaltet die ausimplementierung der Logik.

- Die Junit-Tests zur Sprache befinden sich unter
[Tests](./src/test)

---
# Beispiel in Java
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

# Beispiel in Excuisite

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