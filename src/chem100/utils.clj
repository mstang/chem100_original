(ns chem100.utils)

(split "root:*:0:0:admin:/var/root:/bin/sh" #"\.")

(defn find-class-name [package]
  (last (split (str package) #"\.")))


