# Projekat iz predmeta ISA i MRS - Tim 15

Članovi tima:
* Student 1 - Tatjana Gavrilović SW-53/2018
* Student 2 - Ivan Čolak SW-23/2013
* Student 3 - Laslo Sabadi Baranji SW-51/2018
* Student 4 - Daniel Božanić SW-63/2018

# Uputstvo za pokretanje projekta

## Neophodni programi
Da bi se aplikacija pokrenula potrebno je instalirati sledeće programe:
* [MySQL](https://dev.mysql.com/downloads/) baza podataka
* [Eclipse IDE for Enterprise Java and Web Developers](https://www.eclipse.org/downloads/packages/release/2021-03/r/eclipse-ide-enterprise-java-and-web-developers)
* [Node.js](https://nodejs.org/en/) za instalaciju Angular framework-a

### Pokretanje frontend aplikacije
Prvo treba instalirati Angular framework korišćenjem sledeće komande:
```
npm install @angular/cli
```

Premestiti se u folder frontend aplikacije i instalirati dodatne plugine:
```
cd frontend
npm install
```

Pokrenuti aplikaciju:
```
ng serve
```
### Pokretanje SUBP-a

Nakon instaliranja i pokretanja MySQL baze podataka, izvršite skriptu [database.sql](./backend/src/main/resources/database.sql).
Ovom akcijom kreiraće se baza podataka.

### Pokretanje backend aplikacije
Importovati projekat u Eclipse IDE koji se nalazi u folderu ```backend```.

Odabrati opciju ```File -> Import -> Maven -> Existing Maven projects```.