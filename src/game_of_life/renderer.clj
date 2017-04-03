(ns game-of-life.renderer
  (:require [clj-launchpad :as launchpad]
            [game-of-life.game :as game]))

(defn draw-grid
  "Draws a grid on the launchpad"
  [device grid]
  (game/grid-map
    (fn [x y v]
      (if (true? v)
        (launchpad/draw-grid device x y :green)
        (launchpad/draw-grid device x y :off)))
    grid))
