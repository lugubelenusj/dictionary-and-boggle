This is a dictionary data structure is also used to support the game: boggle.

The dictionary data structure is made in the form of a 26-ary tree. It consists of true/false nodes that each have 26 pointers in an array which represent letters. If a node is true, it means that all the pointers from the root to the current node make up a valid word in the dictionary. Each pointer in a represents a letter, so depending on what pointers are used in a sense translates to a word.

For example: the word "cat" would be represented by the root node's 3rd pointer pointing to another node. That node's first pointer would point to yet another node. That last node's 20th pointer would finally point to the last node who's value is true. As long as the node before this one has a value of false, "ca" is not a valid word.

This dictionary is used to also support a boggle program. Boggle is basically a game where a player tries to find more words than the opponent on a board. They can build words by connecting letters with other letters that are above, below, to the side of, or diagonal of that letter.

For this version of the game, the player tries to find as many words as they can and enter it into the GUI. After submitting his or her findings, the computer finds as many words as it can and returns both the player's and the computers score. The computer finds as many words as it can by recursively going through each tile in the board, and checking that with the 26-ary trie to make sure that it is a valid word.
