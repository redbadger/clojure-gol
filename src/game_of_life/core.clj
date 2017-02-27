(ns game-of-life.core
    (:require [game-of-life.game :refer :all]))

(def initial-grid
  (-> (new-grid 5 5)
    (assoc-cell 1 2 true)
    (assoc-cell 2 2 true)
    (assoc-cell 3 2 true)))

(defn clear
  []
  (print (str (char 27) "[2J"))) ; clear screen

(defn render
  [grid cell empty]
  (print (str (char 27) "[;H")) ; move cursor to the top left corner of the screen
  (println (to-string grid cell empty)))

(defn -main
  "Run some thing!"
  []
  (let [emoji "◻️"]
    (clear)
    (render initial-grid emoji " ")
    (Thread/sleep 1000)
    (loop [grid initial-grid]
      (render grid emoji " ")
      (Thread/sleep 100)
      (recur (game-step grid)))))
