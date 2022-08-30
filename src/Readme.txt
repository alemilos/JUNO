DELETE THIS AFTER PROGRAM IS DONE

TODOS...

MENU:
- Strip the username in text-field so that "alessandro".equals("    alessandro  ")
- Level progress bar with JProgressBar to put in the Menu GUI and also in the Profile
- Have an enum with names of each frame. This will allow to work without using getClass()
- Finish the game settings gui: 2 images (labels) needs to go one over the other. The one in the back will represent
the table style, while the other will represent the card back set by the user. This will be dynamic similarly to the
thing I did with the avatar image.


GAME:
- Make fake screens for loading etc..(pregame needs it)
- NOT playing players will have avatar Image NOT enabled, While when they are playing their avatar will be ENABLED
so will show a color. Maybe we will have some effect on this, like slowly increasing his Height and Width
- Json file for AI: [{name: Harry Potter, avatar: harrypotter.jpg}] ad ogni nome si associa una faccia
- Come far funzionare il main game. Importanti funzioni : nextPlayer(), la carta REVERSE se esce come prima carta
inverte il giro e fa iniziare il mazziere per primo. (da ragionare nel model). Dopo aver fatto nextPlayer, chiamare
la funzione playroundAI e mettere qualche timing per dare l'illusione che stia veramente giocando
(magari con una progress bar inversa). Ad ogni round cambia la ground card e una carta dal deck viene pescata.
Con l'attuale struttura a funzioni del GameGui non è possibile accedere a pannelli specifici. Bisogna inserire il tutto
dentro classi specifiche così da avere campi privati con i JPanel in modo tale da potervi accedere distruttivamente,
senza dover renderizzare ogni volta il tutto sprecando risorse di sistema(anche se un bel sticazzi mi viene da dirlo
data la misura del progetto e data la potenza di calcolo della mia cpu diocane)
- if hovering the deck --> show cards left
- a card match() function will be used to understand if the drag and drop of a card by the user has worked or not.

- Better than doing a BIG side panel: a button that if clicked modifies the size of the side panel (making it bigger)
and giving access to the settings and to the leave game button?? ? Maybe is TOO much.