(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]))

(deftest new-grid-test
  (testing "creates an empty 2x2 grid"
    (is (=
          [[false false]
           [false false]]
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

(deftest assoc-cell-test
  (testing "sets a cell to true"
    (let [empty-grid (new-grid 2 2)
          expected-grid [[true false]
                         [false false]]
          actual-grid (assoc-cell empty-grid 0 0 true)]
      (is (= actual-grid expected-grid))))

  (testing "sets a cell to true"
    (let [empty-grid (new-grid 2 2)
          expected-grid [[false true]
                         [false false]]
          actual-grid (assoc-cell empty-grid 1 0 true)]
      (is (= actual-grid expected-grid)))))

(deftest count-neighbours-test
  (testing "a single cell has 0 neighbours"
    (let [grid [[true false]
                [false false]]
          expected 0
          actual (count-neighbours grid 0 0)]
      (is (= actual expected)))))

(deftest game-step-test
  (testing "a single cell dies"
    (let [grid (assoc-cell (new-grid 2 2) 0 0 true)
          expected-grid (new-grid 2 2)
          actual-grid (game-step grid)]
      (is (= actual-grid expected-grid))))
  (testing "two cells survive"
    (let [grid [[true true]
                [false false]]
          expected-grid [[false false]
                         [true true]]
          actual-grid (game-step grid)]
      (is (= actual-grid expected-grid))))
  (testing "three cells survive"
    (let [grid [[true true]
                [true false]]
          expected-grid [[true true]
                         [true true]]
          actual-grid (game-step grid)]
      (is (= actual-grid expected-grid)))))
