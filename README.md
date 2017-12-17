[![Build status](https://api.travis-ci.org/Walingar/ctddev-kotlin-demo-2017.svg?branch=master)](https://travis-ci.org/Walingar/ctddev-kotlin-demo-2017)
# Kotlin course 2017
## Рыкунов Николай

Ветка Рыкунова Николая студента М3235 по курсу Котлин 2017

[Текущие баллы](https://docs.google.com/spreadsheets/d/1rpBErIUVnsn0_QTr-PFzxGzP3exrx2vTqB1tMRyDSB8/edit#gid=0&range=A91)

# Задание 1
## Алгоритм Кнута-Морриса-Пратта

Алгоритм который осуществляет поиск подстроки в строке.

Интерфейс:
```kotlin
/**
* Searches substring [k] in string [s].
* @param[s] the string(without #), where finds substring.
* @param[k] the substring(without #).
* @return the index of first occurrence of the substring in the string,
 * -1 if there is no such substring.
*/
fun searchSubstring(s:String, k: String): Int
```
# Задание 2
## Алгоритм Кнута-Морриса-Пратта (приложение для Android)
### Скриншоты:
Вид приложения без ввода данных

![alt text](artifacts/Without%20anything.png "Without anything")

Подстрока не содержится в строке

![alt text](artifacts/Substring%20doesn't%20occurrence.png "Substring doesn't occurrence")

Подстрока содержится в строке

![alt text](artifacts/pineaple%20and%20apple.png "pineapple and apple")

Текст в котором содержится подстрока

![alt text](artifacts/Big%20text%20with%20apple.png "Big text with apple")

# Разное

## Gradle

Полезные команды

* `./gradlew assemble` собрать проект
* `./gradlew test` собрать и запустить тесты
* `./gradlew check` собрать, запустить все проверки (тесты и style-checker)
* `./gradlew ktlintCheck` запустить style-checker (ошибки в `build/reports/ktlint`)
* `./gradlew ktlintFormat` попытаться отформатировать код (работает не всегда :-( )
