# Zadanie 8 - Spring & Thymeleaf

### Zadanie ToDoList cz.1 - 5 pkt.  

Napisz program typu ToDoList. :clipboard:

Program powinien być napisany z wykorzystaniem frameworka Spring Boot oraz Thymeleaf.

Główną funkcją aplikacji jest wyświetlanie listy zadań do wykonania.

Użytkownik może dodawać kolejne zadania za pomocą odpowiedniego formularza, zawierającego następujące pola:
- **opis** - co trzeba zrobić,
- **kategoria** - do wyboru z rozwijanej listy (min. 2 pozycje, np. praca/dom),
- **priorytet** - od 1 (najwyższy) do 5 (najniższy) - walidacja (nie można wpisać liczby spoza zakresu),
- **termin** - można wpisać tylko datę z przyszłości.

Po wprowadzeniu nowego zadania jest ono zapisywane w pamięci (opcja dla chętnych: zapisywanie również do pliku json), a następnie wyświetlana jest lista z wszystkimi zadaniami do wykonania (każdy element listy zawiera opis, kategorię, priorytet i termin).

Na stronie z wszystkimi zadaniami powinien się też znaleźć przycisk **| dodaj nowe zadanie |**, dzięki któremu szybko wyświetlimy formularz do wprowadzenia nowego zadania.

Po uruchomieniu aplikacji stroną główną jest lista z wszystkimi zadaniami (pusta, jeśli jeszcze nie ma żadnych zadań, wówczas tylko przycisk dodawania nowego zadania).


##### Wymagania:
   - Przy tworzeniu obu widoków wykorzystaj integrację warstwy frontendu z backendem za pomocą wyrażeń dostępnych w Thymeleafie.
   - Widoki powinny być estetyczne i czytelne (warto wykorzystać np. bibliotekę bootstrap).
   - Po stronie backendu dodaj odpowiednie kontrolery i endpointy.
   - Aplikacja powinna być napisana obiektowo, zgodnie ze wzorcem **MVC** oraz zgodnie z zasadami **clean-code**. 
  
<br/>Maksymalną liczbę punktów za zadanie można otrzymać tylko wówczas, jeśli wszystkie powyższe polecenia zostały wykonane.  


## Forma oddania zadania
Utwórz nowy branch o nazwie wg konwencji `8_imieNazwisko`, np. `8_MariuszSzymanski`.<br/>
Pamiętaj, żeby nowy branch utworzyć wychodząc z aktualnego brancha master.
Wykonaj zadanie na podanym branchu. <br/>
:new: Utwórz Pull Request ze swojego brancha na master :heavy_exclamation_mark:


Dopiero tak zgłoszone zadanie podlega ocenie.

---

## Punktacja

Do zdobycia jest :five: pkt.
Zadania oddane po terminie są oceniane na maksymalnie połowę punktów.

#### Powodzenia!

## :calendar: Termin oddania: piątek 22.01.2021
# toDoList
