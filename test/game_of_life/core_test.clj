(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]))

(deftest new-grid-test
  (testing "creates an empty 2x2 grid"
    (is (=
          [[false false] [false false]]
          (new-grid 2 2))))
  (testing "creates an empty 3x3 grid"
    (is (=
          [[false false false]
           [false false false]
           [false false false]]
          (new-grid 3 3))))
  (testing "creates an empty 1x2 grid"
    (is (=
          [[false]
           [false]]
          (new-grid 1 2)))))
