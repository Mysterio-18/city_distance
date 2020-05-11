# city_distance
Program změří vzdálenost mezi dvěma městy.

## Předpoklady
Java 11 nebo vyšší. Oracle verze např. [zde](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) nebo OpenJDK verze. Pro systém Debian [návod na instalaci zde](https://www.digitalocean.com/community/tutorials/how-to-install-java-with-apt-on-debian-10).

## Instalace
K vytvoření .jar souboru použijte [maven](https://maven.apache.org/).
```bash
maven package
```
Soubor .jar poté naleznete v adresáři `./target`
## Použití

```bash
java -jar city_distance.jar město1 město2 [jednotky]
java -jar city_distance.jar Praha Brno
```

Jako jednotky lze zvolit míle. Implicitně jsou nastaveny kilometry.
```bash
java -jar city_distance.jar Praha Brno míle
```

Pro nápovědu lze spustit s parametry `-h` nebo `--help`.
