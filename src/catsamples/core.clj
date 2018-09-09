(ns catsamples.core
  (:gen-class)
  (:require [cats.core :as m])
  (:require [cats.builtin])
  (:require [cats.monad.maybe :as maybe]))

(defn add-tail
  [hd]
  (m/mappend
   hd
   (maybe/just [4 5 6])))

(defn -main
  [& args]
  (let [res
        (m/mlet [nums1 (add-tail (maybe/just [1 2 3]))
                 nums2 (add-tail (maybe/nothing))]
                (m/return (m/mappend nums1 nums2)))]
    (if (maybe/just? res)
      (println "Res" (maybe/from-maybe res))
      (println "None"))))
