**Scenario Walkthrough:**

First the User runs the `cli.CliMain.java` file through IntelliJ or through the command line by running the following in the project directory:

`$ gradle build -x test`

`$ gradle run`

---
Upon running `cli.CliMain.java`, a `CliDriver` is created along with a `CliPresenter` (which is given the `CliDriver`) and a `CliController` (which is given the `CliDriver` and the `CliPresenter`), then the controller is asked to run the CLI application. The controller will prompt the user for input and lead them through the various menus available in the CLI implementation of our program. Each time an input is recieved, the controller calls upon the appropriate use case to carry out the necessary operations. Our skeleton program's functionality can be outlined by the following:

---
Once the user has started the command line application, they are at the Login Menu and will be prompted to login or create an account. To login, an existing user enters their user name and then their password once prompted.

To create an account, the user can enter '`create`', the program will take them to the Create User Menu and prompt them for their username (which must be unique), a password, their location, and an interest of theirs. After successful account creation, the user will be logged into their new account.

Once logged in, the user will see the User Menu- which gives them additional menu options: `conversations`, `friends`, `userinfo`, and `logout`. The first three of these are not implemented in this skeleton program, but the user is able to use `logout`.

When the user enters `logout` at the User Menu, they will be logged out and returned to the Login Menu.

The User can enter `exit` at the Login Menu or the Create User Menu to stop the program.
