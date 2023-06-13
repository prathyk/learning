-- import Data.List
-- import System.IO

-- main = putStrLn "Hello World"

-- type Val = Int

-- -- getchar :: Val -> (Char, Val)
-- -- getchar v = ('a', 3)

-- -- get2Chars :: Val -> (String, Val)
-- -- -- get2Chars u1 = (a ++ b, u3) where (a, u2) = getchar u1
-- -- --                                   (b, u3) = getchar u2
-- -- get2Chars u1 = let (a, u2) = getchar u1 in
-- --                let (b, u3) = getchar u2 in
-- --                ([a, b], u3)

-- -- get4Chars :: Val -> String
-- -- get4Chars u1 = a ++ b where (a, u2) = get2Chars u1
--                             -- (b, _) = get2Chars u2


-- class MNad m where
--   unit :: a -> m a
--   bind :: m a -> (a -> m b) -> m b


-- type IO' a = Val -> (a, Val) 
--   unit :: a -> IO' a
--   unit x = \u0 -> (x, u0)

--   bind :: m a -> (a -> m b) -> m b
--   bind m k = \u0 -> let (x, u1) = m u0 in
--                     let (y, u2) = k x u1 in
--                     (y, u2)


-- getchar :: IO' Char

-- get2Chars :: IO' String
-- get2Chars = \u0 -> let (a, u1) = getchar u0 in
--                    let (b, u2) = getchar u1 in
--                    let r = a ++ b in
--                    (r, u2)

-- get4Chars :: IO' String
-- get4Chars = get2Chars `bind` \a ->
--             get2Chars `bind` \b ->
--             unit (a ++ b)


-- liftM f action = \u0 -> let (x, u1) = action u0 in
--                         ((f x), u1)



-- ---

-- main = let a0 = readVar varA
--            _ = writeVar varA 1
--            a1 = readVar varA
--        print (a0, a1)


import Data.IORef

main = do varA <- newIORef 0
          a0 <- readIORef varA
          writeIORef varA 1
          a1 <- readIORef varA
          print (a0, a1)
