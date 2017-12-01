[![Build status](https://travis-ci.org/anton-bannykh/ctddev-kotlin-demo-2017.svg?branch=master)](https://travis-ci.org/anton-bannykh/ctddev-kotlin-demo-2017)
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


# Разное

## Gradle

Полезные команды

* `./gradlew assemble` собрать проект
* `./gradlew test` собрать и запустить тесты
* `./gradlew check` собрать, запустить все проверки (тесты и style-checker)
* `./gradlew ktlintCheck` запустить style-checker (ошибки в `build/reports/ktlint`)
* `./gradlew ktlintFormat` попытаться отформатировать код (работает не всегда :-( )
