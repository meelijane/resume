(set-env!
  :dependencies '[[org.clojure/clojure                    "1.8.0"]
                  [org.clojure/clojurescript              "1.7.228"]
                  [pandeiro/boot-http                     "0.7.0"]
                  [hoplon/hoplon                          "6.0.0-alpha15"]
                  [hoplon/boot-hoplon                     "0.1.10"]
                  [adzerk/boot-reload                     "0.4.2"]
                  [adzerk/boot-cljs                       "1.7.170-3"]]
  :resource-paths #{"assets"}
  :source-paths   #{"src/cljs" "src/hl"})

(require
  ; '[clojure.string        :refer [trim]]
  '[adzerk.boot-cljs      :refer [cljs]]
  ; '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
  '[adzerk.boot-reload    :refer [reload]]
  '[hoplon.boot-hoplon    :refer [hoplon prerender]]
  '[pandeiro.boot-http    :refer [serve]])


(deftask dev
  "Build app for local development." 
  []
  (comp
    (watch)
    (serve
      :dir "target_dev"
      :port    8000)
    (speak)
    (hoplon)
    (reload)
    ; This compiles cljs in a way that is fast to compile, easy to debug but
    ; slow to load the page in the browser and with a big FOUC risk.
    (cljs)
    (target :dir #{"target_dev"})))
    ; This is the production compilation strategy. It is much slower to compile
    ; and basically impossible to debug (minified) but sometimes we need to
    ; simulate this behaviour for development/testing.
    ; (cljs :optimizations :advanced)
    ; (prerender)))
