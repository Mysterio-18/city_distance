# city_distance
Program změří vzdálenost mezi dvěma městy.

## Instalace
K vytvoření .jar souboru použijte [maven](https://maven.apache.org/)
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
