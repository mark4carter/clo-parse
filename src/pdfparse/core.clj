(ns pdfparse.core
	(:import [org.apache.pdfbox.pdmodel PDDocument]
						[org.apache.pdfbox.util PDFTextStripper]
						[java.net URL])
  (:gen-class))

(defn text-of-pdf
  [url]
  (with-open [pd (PDDocument/load (URL. url))]
    (let [stripper (PDFTextStripper.)]
    (.getText stripper pd))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (spit (first args) (text-of-pdf (first (rest args)))))
