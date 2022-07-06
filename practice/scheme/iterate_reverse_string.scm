(require rnrs/mutable-pairs-6)
(require compatibility/mlist)
(define reverse!
  (lambda (s)
    (let loop ((s s) (r '()))
        (if (null? s) r
          (let ((d (mcdr s)))
            (set-cdr! s r)
            (loop d s))))))
(display (reverse! (list->mlist '(1 2 3 4 5))))
