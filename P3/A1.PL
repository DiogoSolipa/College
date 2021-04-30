homem(joao).
homem(rui).
homem(manuel).
homem(ricardo).
mulher(maria).
mulher(ana).
mulher(rita).
mulher(silvia).
progenitor(joao, maria).
progenitor(joao, rui).
progenitor(manuel, joao).
progenitor(ricardo, manuel).
progenitor(ana, rui).
progenitor(rita, joao).
progenitor(rita, silvia).

pai(X, Y) :- homem(X), progenitor(X, Y).
avo(X, Y) :- progenitor(X, Z), progenitor(Z, Y).

antepassado(X, Y) :- progenitor(X, Y).
antepassado(X, Y) :- progenitor(X, Z), antepassado(Z, Y).

irmao(X, Y) :- progenitor(Z, X), progenitor(Z, Y), X\=Y.

tio(X, Y) :- irmao(X, Z), progenitor(Z, Y).

parente(X, Y) :- antepassado(X, Y).
parente(X, Y) :- antepassado(Y, X).
parente(X, Y) :- antepassado(C, X), antepassado(C, Y), X\=Y.
