(ns guestbook-reagent.core)

(-> (.getElementById js/document "content")
    (.-innerHTML)
    (set! "Hello World!"))
