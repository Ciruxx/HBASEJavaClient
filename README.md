# HBASE Docs

## Istruzioni per l'avvio

I path relativi si riferiscono sempre alla root del progetto.

### Installazione hbase

 - Installare Java 8 sul proprio pc
 - Impostare la variabile di ambiente JAVA_HOME, assicurarsi che nel bashrc (o simili) ci siano queste due righe, di solito in fondo al file.
    - export JAVA_HOME=$(readlink -f /usr/bin/java | sed "s:bin/java::")
    - export PATH=$PATH:$JAVA_HOME/bin
 - Copiare la cartella ./hbase/habase nella propria home
 - Modificare il file ./hbase/hbase-1.4.10/conf/hbase-site sostituendo la stringa TUO-NOME-UTENTE con il proprio nome utente
 - Avviare hbase con lo script start-hbase nella cartella ./hbase/hbase-1.4.10/bin

### Avvio del programma Java
 
 - Aggiungere al proprio classpath tutte le librerie contenute in ./jars
 - Avviare il file ./src/com/cy/Main
 
## Bug noti

Durante l'esecuzione del programma potrebbero apparire balene.

La funzione che richiede l'input all'utente sembra non funzionare a dovere, continua a chiedere input all'utente anche se dovrebbe terminare l'esecuzione. 

A volte il programma potrebbe offendere, ma lo stiamo educando.

### Comandi Shell

Comando di get: 

`get 'tabella', 'row name'`

Comando di put: 

`put 'tabella', 'row name', 'family:qualifier', 'value'`

Ottenere tutte le row appartenenti ad una certa family: 

`scan 'tabella', {COLUMNS => 'family'}`

Ottenere tutte le row appartenenti ad una certa family e un qualifier: 

`scan 'tabella', {COLUMNS => 'family:qualifier'}`

### Compatibilità

Testato su Manjaro Linux e Ubuntu.

### Credits

Ciro Santillo && Giammarco Sommaini

> Written with [StackEdit](https://stackedit.io/).