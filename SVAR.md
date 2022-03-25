## Hva syntes du om denne semesteroppgaven? Har du forslag til hvordan den kan gjøres bedre eller enklere?

Jeg synes det var en spennende semesteroppgave, men jeg følte at mange deler av oppgaven var unødvendig å gjennomføre,
hvis målet var å lage generisk grid kunne oppgaven heller prøvd å utnytte funksjonaliteten med dette. Jeg følte også at
det var steder hvor jeg heller ville gjøre en metode på min måte men oppgaven fikk det til å virke som det ikke var lov.
Blant annet i metodene som finner linjer og sletter en full linje.


## Hvor i koden din benytter du deg av gjenbruk av kode? Er det noen steder du føler du ikke klarte å gjenbruke kode på en god måte?

Jeg synes det var vanskelig å gjenbruke kode i tegning i JFrame, jeg har laget en hjelpemetode slik at jeg kan tegne en boks rundt TetrisBoard
Jeg brukte mye iterator metoden når jeg skulle gå igjennom griden i forskjellige steder.

## Hvilke grep gjør vi for å øke modulariteten i koden? Gi noen eksempeler.

Vi bruker blant annet modularitet for å kunne lage objekter som Tile, Coordinate, og Cooridate Item, dette gjør at vi fort kan
lage forskjellige instances av samme object og vi vet hva den lagrer og hvilke metoder den har, vi kan også fort legge til og fjerne
metoder og da funker det for alle av samme type. Ved bruk av dette kan vi gjenbruke mye av koden vår.