(ns game-of-life.game-test
  (:require [clojure.test :refer :all]
            [game-of-life.game :refer :all]))

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

  (testing "two cells die"
    (let [grid [[true true]
                [false false]]
          expected-grid (new-grid 2 2)
          actual-grid (game-step grid)]
      (is (= actual-grid expected-grid))))

  (testing "three cells survive and another one gets created"
    (let [grid [[true true]
                [true false]]
          expected-grid [[true true]
                         [true true]]
          actual-grid (game-step grid)]
      (is (= actual-grid expected-grid))))

  (testing "cell with four neighbours dies"
    (let [grid [[false true false]
                [true true true]
                [false true false]]
          expected-grid [[true true true]
                         [true false true]
                         [true true true]]
          actual-grid (game-step grid)]
      (is (= actual-grid expected-grid)))))

(deftest to-string-test
  (testing "prints a single cell"
    (let [grid [[true]]]
      (is (= "o" (to-string grid "o" ".")))))

  (testing "prints multiple cells"
    (let [grid [[true false true true]]]
      (is (= "o . o o" (to-string grid "o" ".")))))

  (testing "prints multiple rows"
    (let [grid [[true false true true]
                [false false true false]]]
      (is (= "o . o o\n. . o ." (to-string grid "o" "."))))))
