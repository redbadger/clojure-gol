(ns game-of-life.core
    (:require [game-of-life.game :refer :all]))

(def initial-grid
  (random-grid 80 60 0.4))

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
