(ns astar
  "FIXME: my new org.corfield.new/scratch project.")

(defn -main
  "Invoke me with clojure -M -m astar"
  [& args]
  (println "-main with" args))

(defn neighbors 
  ([size yx] (neighbors [[-1 0] [1 0] [0 -1] [0 1]] size yx))
  ([deltas size yx] (filter (fn [new-yx] (every? #(< -1 % size) new-yx))
                            (map #(vec (map + yx %)) deltas))))


(defn estimate-cost [step-cost-est size y x]
  (* step-cost-est (- (* 2 size) x y 2)))

(defn path-cost [node-cost cheapest-nbr]
  (+ node-cost 
     (or (:cost cheapest-nbr) 0)))

(defn total-cost [new-cost step-cost-est size y x]
  -1)
  ; (+ new-cost
  ;    (estimate-cost step-cost-est size y x)))

(defn min-by [f coll]
  (when (seq coll)
      (reduce (fn [min next] 
                  (if (> (f min) (f next)) 
                      next 
                      min)) 
              coll)))

(defn astar [start-yx step-est cell-costs]
  (let [size (count cell-costs)]
      (loop [steps 0
             routes (vec (replicate size (vec (replicate size nil))))
             work-todo (sorted-set [0 start-yx])]
          (if (empty? work-todo)
            [(peek (peek routes)) :steps steps]
            (let [[_ yx :as work-item] (first work-todo)
                  rest-work-todo (disj work-todo work-item)
                  nbr-yxs (neighbors size yx)
                  cheapest-nbr (min-by :cost (keep #(get-in routes %)
                                                   nbr-yxs))
                  newcost (path-cost (get-in cell-costs yx)
                                     cheapest-nbr)
                  oldcost (:cost (get-in routes yx))]
              (if (and oldcost (>= newcost oldcost))
                (recur (inc steps) routes rest-work-todo)
                (recur (inc steps)
                       (assoc-in routes yx
                                 {:cost newcost :yxs (conj (:yxs cheapest-nbr [])
                                                           yx)})
                       (into rest-work-todo
                             (map
                               (fn [w]
                                 (let [[y x ] w]
                                   [(total-cost newcost step-est size y x) w]))
                               nbr-yxs)))))))))

(comment
  (require '[astar] :reload-all)

  (astar [0 0]
         90
         [[ 1 1 1 1 1]
          [999 999 999 999 1]
          [1 1 1 1 1]
          [1 999 999 999 999]
          [1 1 1 1 1]])
; [{:cost 17,
;   :yxs
;   [[0 0] [0 1] [0 2] [0 3] [0 4]
;    [1 4]
;    [2 4] [2 3] [2 2] [2 1] [2 0]
;    [3 0]
;    [4 0] [4 1] [4 2] [4 3] [4 4]]}
;  :steps
;  76]

  (astar [0 0]
         900
         [[ 1 1 1 2 1]
          [1 1 1 999 1]
          [1 1 1 999 1]
          [1 1 1 999 1]
          [1 1 1 1 1]])
; [{:cost 9,
;   :yxs [[0 0] [0 1] [0 2] [1 2] [2 2] [3 2] [4 2] [4 3] [4 4]]}
;  :steps
;  134]

  (astar [0 0]
         900
         [[ 1 1 1 2 1]
          [1 1 1 999 1]
          [1 1 1 999 1]
          [1 1 1 999 1]
          [1 1 1 666 1]])
; [{:cost 10,
;   :yxs [[0 0] [0 1] [0 2] [0 3] [0 4] [1 4] [2 4] [3 4] [4 4]]}
;  :steps
;  132]

  (astar [0 0]
         900
         [[ 1 1 1 2 1]
          [66 66 1 999 1]
          [1 1 666 999 1]
          [1 1 1 999 1]
          [1 1 1 1 1]])
  (vec (neighbors 4 [2 2]))
  
  (estimate-cost 900 5 3 3)
  (path-cost 900 {:cost 1})
  (total-cost 0 900 5 0 0)
  (total-cost 1000 900 5 3 4)
  (total-cost (path-cost 900 {:cost 1}) 900 5 3 4)

  (min-by :cost [{:cost 100} {:cost 36} {:cost 9}]))
