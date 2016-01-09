(ns pdfparse.core
	(:import [org.apache.pdfbox.pdmodel PDDocument]
						[org.apache.pdfbox.util PDFTextStripper]
						[java.net URL])
  (:gen-class))

(def emailReg "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
(def zipReg "[0-9]{5}(?:-[0-9]{4})?")

(defn text-of-pdf
  [url]
  (with-open [pd (PDDocument/load (URL. url))]
    (let [stripper (PDFTextStripper.)]
    (.getText stripper pd))))

(defn separate-cat
	[results]
	{:email (remove nil? (for [result results] (re-seq (re-pattern (str emailReg)) result)))
		:zip (remove nil? (for [result results] (re-seq (re-pattern (str zipReg)) result)))})

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (spit (first args)
  	(separate-cat
	  	(re-seq 
	  		(re-pattern (str emailReg "|" zipReg)) 
	  			(text-of-pdf (first (rest args)))))))
