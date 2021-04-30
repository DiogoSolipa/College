minimo(X, Y, Z) :- Z < X, Z < Y.

membr(H, [H|_]).
membr(X, [_|T]) :- membr(X, T).

membrchk(H, [H|_]) :- !.
membrchk(X, [_|T]) :- membrchk(X, T).

nonmembr(X, [H|T]) :- X\= H, nonmembr(X, T).
nonmembr(_, []).

fact(0, 1).
fact(X, Y) :- X > 0, X1 is X-1, fact(X1, Z), Y is X*Z.
