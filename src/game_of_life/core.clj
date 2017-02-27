(ns game-of-life.core)

(defn new-grid
  "Creates a new grid width by height"
  [width height]
  (vec (repeat height (vec (repeat width false)))))
