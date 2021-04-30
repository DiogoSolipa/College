/*  
    Predicado ambiguo/4   
    (Lista com simbolos e suas representações, código da menor palavra, menor palavra, simbolos que constituem menor palavra)
*/
/*  ###################################
    #   TESTES FORNECIDOS PELO PROF   #
    ###################################
    1ºExemplo:
        ambiguo( [(a, [0,1,0]), (c, [0,1]), (j, [0,0,1]), (l, [1,0]), (p, [0]), (s, [1]), (v, [1,0,1])], M, T1, T2)   
 
        Output Esperado:
            M = [0, 1]
            T1 = [c]
            T2 = [p, s] 
 
    2ºExemplo:
        ambiguo( [(a, [0,1,1,0]), (b, [0,1,1,1,1,1]), (c, [1,1,0,0,1,1,1,1]), (f, [1,0,1,1,1,0]), (j, [0,1,0]), (l, [0,1,0,0]), (r, [0,1,1,1,0])], M , T1, T2)
 
        Output Esperado: ?
*/
/*Tamanho da lista*/
listlen([],0).
listlen([_|T], Len) :- listlen(T, Y), Len is Y+1.
 
 
/*Predicado para comparar listas*/
cmplists([],[]).
cmplists([H|T], [H1|T1]) :- H=H1, cmplists(T,T1).
 

/*Tamanho da segunda posição do um tuplo passado*/
secondpostuplelen((_,X), Len) :- listlen(X, Len).
 
 
/*appendSecondPosToList(Init, toAppend, Output)*/
/*
[0] [p,[0]]  --> [0,0]
*/
appendSecondPosToList([], [(_,X)], X).
appendSecondPosToList([H|T], [(_,X)], [H,X|W]) :- appendSecondElementsToList(T, X,W).
/*appendSecondPosToList([H|T], Z, [H,T,Z|W]) :- appendSecondElementsToList(T,Z,W).*/
 
 
 
 
 
 
 
/*Compara o tamanho das segundas posições de 2 tuplos retornando o tuplo de acordo com a comparação obtida
    Retorna: tuplo cujo lista representativa do simbolo é menor
*/
cmptuple(X, Y, X) :- secondpostuplelen(X, Xlen), secondpostuplelen(Y, Ylen), Xlen<Ylen. 
 
 
/*Verificar se 2 tuplos são ambiguos:
    Tuplos ambiguos:
        -Os simbolos são diferentes
        -Codigos dos simbolos são iguais  
*/
ambiguous((S1,L1), (S2,L2)):- S1\=S2, listlen(L1, Len1), listlen(L2, Len2), Len1=Len2, cmplists(L1,L2). 
 
 
/*Sort da lista de input de acordo com o tamanho das segundas posições dos tuplos de cada posição da lista
    Nota: Usado o algoritmo isort() que consta nos slides, embora alterado para se adaptar ao dados de input(lista de tuplos)
 
    Insere á cabeça caso o comprimento da lista da segunda posição de cada tuplo seja menor que a anterior
    Caso contrário, insere na cauda
*/
isort(I, S) :- isort(I, [], S).     
isort([], S, S).
isort([X|Xs], SI, SO) :- insord(X, SI, SX), isort(Xs, SX, SO).
 
insord(X, [], [X]).
insord(X, [A|As], [X,A|As]) :- cmptuple(X,A,X).     
insord(X, [A|As], [A|AAs]) :- \+cmptuple(X,A,X), insord(X, As, AAs).
 
 
/*
Permutações possiveis da lista, com tamanho N.
*/
eval([],_).
eval([H|T],Set):- member(H,Set), eval(T,Set).
 
myPermutation(N,Set,L):- 
    length(L,N),
    eval(L,Set).
 
 
/*
loop(From, To, Index):
    -From --> Partida
    -To --> Limite
    -Index --> Posição atual
 
    O que faz este predicado?
        Este predicado basicamente simula um ciclo for.
*/
loop(From,_,From).
loop(From, To, X):-
    From<To,
    Next is From+1,
    loop(Next, To, X).
 
 
/*
PermToNIter(Start, End,Set,L):
    -Start --> inicio do ciclo
    -End --> final do ciclo
    -Set -->Lista de tuplos de Input
    -L -->Output
 
    O que faz este predicado?
        Este predicado, inicialmente ordena a lista de input, através do predicado isort() pelos tamanhos dos códigos representativos dos simbolos 
        De seguida executa o predicato myPermutation() tantas vezes quantas forem indicadas no loop()
        Será mostrado a cada iteração completa as listas de combinaçoes possíveis com tamanho Index.
        O predicado mostrará as listas de combinaçoes possiveis desde o tamanho "Start" até "End"
*/
permToNIter(Start, End, Set, L):-
    isort(Set, Sortedset),
    loop(Start, End, Index),
    myPermutation(Index, Sortedset, L).