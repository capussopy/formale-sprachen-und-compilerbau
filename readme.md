#Exquisite

Semesterarbeit Formale Sprachen

FFHS - FAC BE-Sa-1

Jan Capasso

Siehe [Beispiele.pdf](./Beispiele.pdf)

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