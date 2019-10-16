# HBASE Docs

## Il cacchio che ci abbiamo capito fino ad ora﻿

### Comandi Shell

Comando di get: 
`get 'tabella', 'row name'`
Comando di put: 
`put 'tabella', 'row name', 'family:qualifier', 'value(qualifier)'`
Ottenere tutte le row appartenenti ad una certa family: 
`scan 'tabella', {COLUMNS => 'family'}`
Ottenere tutte le row appartenenti ad una certa family e un qualifier: 
`scan 'tabella', {COLUMNS => 'family:qualifier'}`

> Written with [StackEdit](https://stackedit.io/).