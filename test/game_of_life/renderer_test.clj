(ns game-of-life.renderer-test
  (:require [clojure.test :refer :all]
            [bond.james :as bond :refer [with-stub]]
            [game-of-life.renderer :as renderer]
            [clj-launchpad :as launchpad]))

(def lpad :lpad)

(deftest renderer-test
  (testing "lights up top left corner light"
    (with-stub [[launchpad/draw-grid (fn [& args] args)]]
      (let [initial-grid [[true false]
                          [false false]]]
        (renderer/draw-grid lpad initial-grid)
        (is (= '(:lpad 0 0 :green) (-> launchpad/draw-grid bond/calls (nth 0) :args)))
        (is (= '(:lpad 1 0 :off) (-> launchpad/draw-grid bond/calls (nth 1) :args)))
        (is (= '(:lpad 0 1 :off) (-> launchpad/draw-grid bond/calls (nth 2) :args)))
        (is (= '(:lpad 1 1 :off) (-> launchpad/draw-grid bond/calls (nth 3) :args))))))

  (testing "lights up bottom right corner"
    (with-stub [[launchpad/draw-grid (fn [& args] args)]]
      (let [initial-grid [[false false]
                          [false true]]]
        (renderer/draw-grid lpad initial-grid)
        (is (= '(:lpad 0 0 :off) (-> launchpad/draw-grid bond/calls (nth 0) :args)))
        (is (= '(:lpad 1 0 :off) (-> launchpad/draw-grid bond/calls (nth 1) :args)))
        (is (= '(:lpad 0 1 :off) (-> launchpad/draw-grid bond/calls (nth 2) :args)))
        (is (= '(:lpad 1 1 :green) (-> launchpad/draw-grid bond/calls (nth 3) :args))))))

  (testing "lights up bottom right and top left"
    (with-stub [[launchpad/draw-grid (fn [& args] args)]]
      (let [initial-grid [[true false]
                          [false true]]]
        (renderer/draw-grid lpad initial-grid)
        (is (= '(:lpad 0 0 :green) (-> launchpad/draw-grid bond/calls (nth 0) :args)))
        (is (= '(:lpad 1 0 :off) (-> launchpad/draw-grid bond/calls (nth 1) :args)))
        (is (= '(:lpad 0 1 :off) (-> launchpad/draw-grid bond/calls (nth 2) :args)))
        (is (= '(:lpad 1 1 :green) (-> launchpad/draw-grid bond/calls (nth 3) :args)))))))
