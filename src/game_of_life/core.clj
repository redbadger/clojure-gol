(ns game-of-life.core
    (:require [game-of-life.game :refer :all]
              [game-of-life.renderer :as r]
              [clj-launchpad :as launchpad]))

(def lpad (launchpad/open))

(def initial-grid
  (random-grid 9 8 0.35))

(defn -main
  "Run some thing!"
  []
  (loop [grid initial-grid]
    (r/draw-grid lpad grid)
    (Thread/sleep 100)
    (recur (game-step grid))))
