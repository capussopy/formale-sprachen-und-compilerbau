# Semesterarbeit Formale Sprachen

# Java Code
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

# Meine Sprache

    set 100 as max
    execute hugo with max

    task hugo takes max(
    set 1 as count
    until count lower or equal than max(
    in case that count modulo 7 equal to 0(
                                            ...
                                            )
    fallback(
            ...
            )
        )
      )
    )