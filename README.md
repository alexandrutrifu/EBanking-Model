# Proiect POO 2024
MEDITEAZA LA FACADE PENTRU COMMANDPARSER SI APPMANAGER
## Structură

Aplicația a fost modularizată cu ajutorul următoarelor pachete:

- `App`
  - `EBankingApp`, clasa principală a aplicației (*Singleton*), care se ocupă de inițializarea aplicației și de gestionarea interacțiunii cu utilizatorul
  - `AppManager` - se ocupă de gestionarea serviciilor aplicației
  - `Proxy` - interfață ce definește funcționalitatea de logging a acțiunilor efectuate de utilizatorii aplicației, respectiv a erorilor întâmpinate

- `Entities`
  - `Account`
  - `User`
  - `Stock`

- `Exceptions` - conține excepțiile custom definite în cadrul aplicației

- `Parsers`
    - `CommandParser` - se ocupă de parsarea fișierelor de tip *commands.txt*
    - `ExchangeRatesParser` - se ocupă de parsarea fișierelui *exchangeRates.csv*

- `EntityLister` - conține funcționalitățile de listare a informațiilor despre utilizatori și portofoliile acestora

- `PersonalActions`
// TODO
- `Transactions`
//TODO
  

## Design Patterns

### Singleton

- folosit pentru crearea unei singure instanțe a clasei App

### Proxy

- folosit pentru a media accesul la serviciile reale ale aplicației (Transaction, PersonalAction)

### Observer

- folosit pentru implementarea funcționalității de notificare a utilizatorilor în cazul în care se modifică starea unui cont

### Iterator

- folosit pentru a itera prin intrările Map-ului asociat prețurilor acțiunilor

### Command

- folosit pentru a separa logica de procesare a comenzilor de interfața cu utilizatorul

## Bonus implemented features

- [x] Logging feature - application logs all the actions performed by the users
- [x] Transaction history - users can see the history of transactions performed on their accounts
- [x] Additional error handling - application handles corner cases such as unregistered users, invalid commands, etc.
- [ ] Notifications - users are notified when their account balance is below a certain threshold