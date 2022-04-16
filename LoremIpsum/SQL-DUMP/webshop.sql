-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 16. Jan 2021 um 17:02
-- Server-Version: 10.4.11-MariaDB
-- PHP-Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `webshop`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `description` varchar(200) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `stock` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `image` text CHARACTER SET ascii COLLATE ascii_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `product`
--

INSERT INTO `product` (`id`, `description`, `name`, `price`, `stock`, `quantity`, `image`) VALUES
(1, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', 'Lorem Ipsum', 20, 10, 0, 'iVBORw0KGgoAAAANSUhEUgAAADoAAABbBAMAAADQPYvkAAAACXBIWXMAAAsSAAALEgHS3X78AAAAMFBMVEVHcEwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAlTPQ5AAAAD3RSTlMARHcRzLvuZiLdmTOIVarAvsGYAAABkUlEQVRIx+3VvUrDUBTA8aOpVluC3V1KNhEkg6tQEOfqIKiLg4NuFQRxUxxd/HoB8QXq5KqLu2/gI2hrm/pR+3dIb9N7k1wqCA72TCf8KLnn46Yi/y6AWt9jfm6TpdkUzZYBuPCTNB8inCfpPiqKcc32kHZcnwAa6wDsmupUgG1fjgF2TM0BDV9EboEPU++ALRERF+DI0GrvdVXg3lDgPcxOgTNd3aiQDPCp66ietnQdA17CdASo65oBSt3KgY6uU8BydMDm7+l4pI4qbtAz51QLwnq/4t2oR8W96Tqh97lkTKGszaho6KGa7wgQ+IZO9u9G3dwNl769ulTajfB1aidvYpqJHloSU+dZ5cFjXGW00s3XJEHlJOQVSVRZeKgEGwd//1mZAgrd3FstA83ro0RVx28UbErHtymLVm0na8fznntXP6Y1kYlN4CpNZV5d/UR1gddUdfSrZ6hY1f7bbHQbEzRjO7PT+9TFtCX5mfRe2fs8yIwatvkGRctudPbS9qrtedMpO2n87Q31RzqMv45vnEv/wxKJ/6wAAAAASUVORK5CYII='),
(2, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', 'Dolor Sit', 10, 10, 0, 'iVBORw0KGgoAAAANSUhEUgAAADoAAABbBAMAAADQPYvkAAAACXBIWXMAAAsSAAALEgHS3X78AAAAMFBMVEVHcEwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAlTPQ5AAAAD3RSTlMARHcRzLvuZiLdmTOIVarAvsGYAAABkUlEQVRIx+3VvUrDUBTA8aOpVluC3V1KNhEkg6tQEOfqIKiLg4NuFQRxUxxd/HoB8QXq5KqLu2/gI2hrm/pR+3dIb9N7k1wqCA72TCf8KLnn46Yi/y6AWt9jfm6TpdkUzZYBuPCTNB8inCfpPiqKcc32kHZcnwAa6wDsmupUgG1fjgF2TM0BDV9EboEPU++ALRERF+DI0GrvdVXg3lDgPcxOgTNd3aiQDPCp66ietnQdA17CdASo65oBSt3KgY6uU8BydMDm7+l4pI4qbtAz51QLwnq/4t2oR8W96Tqh97lkTKGszaho6KGa7wgQ+IZO9u9G3dwNl769ulTajfB1aidvYpqJHloSU+dZ5cFjXGW00s3XJEHlJOQVSVRZeKgEGwd//1mZAgrd3FstA83ro0RVx28UbErHtymLVm0na8fznntXP6Y1kYlN4CpNZV5d/UR1gddUdfSrZ6hY1f7bbHQbEzRjO7PT+9TFtCX5mfRe2fs8yIwatvkGRctudPbS9qrtedMpO2n87Q31RzqMv45vnEv/wxKJ/6wAAAAASUVORK5CYII='),
(3, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', 'Sit Amet', 20, 10, 0, 'iVBORw0KGgoAAAANSUhEUgAAADoAAABbBAMAAADQPYvkAAAACXBIWXMAAAsSAAALEgHS3X78AAAAMFBMVEVHcEwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAlTPQ5AAAAD3RSTlMARHcRzLvuZiLdmTOIVarAvsGYAAABkUlEQVRIx+3VvUrDUBTA8aOpVluC3V1KNhEkg6tQEOfqIKiLg4NuFQRxUxxd/HoB8QXq5KqLu2/gI2hrm/pR+3dIb9N7k1wqCA72TCf8KLnn46Yi/y6AWt9jfm6TpdkUzZYBuPCTNB8inCfpPiqKcc32kHZcnwAa6wDsmupUgG1fjgF2TM0BDV9EboEPU++ALRERF+DI0GrvdVXg3lDgPcxOgTNd3aiQDPCp66ietnQdA17CdASo65oBSt3KgY6uU8BydMDm7+l4pI4qbtAz51QLwnq/4t2oR8W96Tqh97lkTKGszaho6KGa7wgQ+IZO9u9G3dwNl769ulTajfB1aidvYpqJHloSU+dZ5cFjXGW00s3XJEHlJOQVSVRZeKgEGwd//1mZAgrd3FstA83ro0RVx28UbErHtymLVm0na8fznntXP6Y1kYlN4CpNZV5d/UR1gddUdfSrZ6hY1f7bbHQbEzRjO7PT+9TFtCX5mfRe2fs8yIwatvkGRctudPbS9qrtedMpO2n87Q31RzqMv45vnEv/wxKJ/6wAAAAASUVORK5CYII='),
(4, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', 'Ipsum Dolor', 30, 10, 0, 'iVBORw0KGgoAAAANSUhEUgAAADoAAABbBAMAAADQPYvkAAAACXBIWXMAAAsSAAALEgHS3X78AAAAMFBMVEVHcEwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAlTPQ5AAAAD3RSTlMARHcRzLvuZiLdmTOIVarAvsGYAAABkUlEQVRIx+3VvUrDUBTA8aOpVluC3V1KNhEkg6tQEOfqIKiLg4NuFQRxUxxd/HoB8QXq5KqLu2/gI2hrm/pR+3dIb9N7k1wqCA72TCf8KLnn46Yi/y6AWt9jfm6TpdkUzZYBuPCTNB8inCfpPiqKcc32kHZcnwAa6wDsmupUgG1fjgF2TM0BDV9EboEPU++ALRERF+DI0GrvdVXg3lDgPcxOgTNd3aiQDPCp66ietnQdA17CdASo65oBSt3KgY6uU8BydMDm7+l4pI4qbtAz51QLwnq/4t2oR8W96Tqh97lkTKGszaho6KGa7wgQ+IZO9u9G3dwNl769ulTajfB1aidvYpqJHloSU+dZ5cFjXGW00s3XJEHlJOQVSVRZeKgEGwd//1mZAgrd3FstA83ro0RVx28UbErHtymLVm0na8fznntXP6Y1kYlN4CpNZV5d/UR1gddUdfSrZ6hY1f7bbHQbEzRjO7PT+9TFtCX5mfRe2fs8yIwatvkGRctudPbS9qrtedMpO2n87Q31RzqMv45vnEv/wxKJ/6wAAAAASUVORK5CYII='),
(5, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores ', 'LoFi Ipsum', 100, 20, 0, 'iVBORw0KGgoAAAANSUhEUgAAADoAAABbBAMAAADQPYvkAAAACXBIWXMAAAsSAAALEgHS3X78AAAAMFBMVEVHcEwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAlTPQ5AAAAD3RSTlMARHcRzLvuZiLdmTOIVarAvsGYAAABkUlEQVRIx+3VvUrDUBTA8aOpVluC3V1KNhEkg6tQEOfqIKiLg4NuFQRxUxxd/HoB8QXq5KqLu2/gI2hrm/pR+3dIb9N7k1wqCA72TCf8KLnn46Yi/y6AWt9jfm6TpdkUzZYBuPCTNB8inCfpPiqKcc32kHZcnwAa6wDsmupUgG1fjgF2TM0BDV9EboEPU++ALRERF+DI0GrvdVXg3lDgPcxOgTNd3aiQDPCp66ietnQdA17CdASo65oBSt3KgY6uU8BydMDm7+l4pI4qbtAz51QLwnq/4t2oR8W96Tqh97lkTKGszaho6KGa7wgQ+IZO9u9G3dwNl769ulTajfB1aidvYpqJHloSU+dZ5cFjXGW00s3XJEHlJOQVSVRZeKgEGwd//1mZAgrd3FstA83ro0RVx28UbErHtymLVm0na8fznntXP6Y1kYlN4CpNZV5d/UR1gddUdfSrZ6hY1f7bbHQbEzRjO7PT+9TFtCX5mfRe2fs8yIwatvkGRctudPbS9qrtedMpO2n87Q31RzqMv45vnEv/wxKJ/6wAAAAASUVORK5CYII='),
(6, 'Well, Marty, I\'m almost eighteen-years-old, it\'s not like I\'ve never parked before. C\'mon. Yeah, you got my homework finished, McFly? Now Biff, don\'t con me. Yeah well look, Marvin, Marvin, you gotta ', 'DeLorean Ipsum', 100, 20, 0, 'iVBORw0KGgoAAAANSUhEUgAAADoAAABbBAMAAADQPYvkAAAACXBIWXMAAAsSAAALEgHS3X78AAAAMFBMVEVHcEwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAlTPQ5AAAAD3RSTlMARHcRzLvuZiLdmTOIVarAvsGYAAABkUlEQVRIx+3VvUrDUBTA8aOpVluC3V1KNhEkg6tQEOfqIKiLg4NuFQRxUxxd/HoB8QXq5KqLu2/gI2hrm/pR+3dIb9N7k1wqCA72TCf8KLnn46Yi/y6AWt9jfm6TpdkUzZYBuPCTNB8inCfpPiqKcc32kHZcnwAa6wDsmupUgG1fjgF2TM0BDV9EboEPU++ALRERF+DI0GrvdVXg3lDgPcxOgTNd3aiQDPCp66ietnQdA17CdASo65oBSt3KgY6uU8BydMDm7+l4pI4qbtAz51QLwnq/4t2oR8W96Tqh97lkTKGszaho6KGa7wgQ+IZO9u9G3dwNl769ulTajfB1aidvYpqJHloSU+dZ5cFjXGW00s3XJEHlJOQVSVRZeKgEGwd//1mZAgrd3FstA83ro0RVx28UbErHtymLVm0na8fznntXP6Y1kYlN4CpNZV5d/UR1gddUdfSrZ6hY1f7bbHQbEzRjO7PT+9TFtCX5mfRe2fs8yIwatvkGRctudPbS9qrtedMpO2n87Q31RzqMv45vnEv/wxKJ/6wAAAAASUVORK5CYII='),
(7, 'Lemme hear everybody say hey-yo! All the ladies say yeaaah! Everybody over thirty do this with your hand! Everybody with a red shirt jump up and down! Yo, everyone whose first name begins with an L wh', 'RickAndMorty Ipsum', 80, 10, 0, 'iVBORw0KGgoAAAANSUhEUgAAADoAAABbBAMAAADQPYvkAAAACXBIWXMAAAsSAAALEgHS3X78AAAAMFBMVEVHcEwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAlTPQ5AAAAD3RSTlMARHcRzLvuZiLdmTOIVarAvsGYAAABkUlEQVRIx+3VvUrDUBTA8aOpVluC3V1KNhEkg6tQEOfqIKiLg4NuFQRxUxxd/HoB8QXq5KqLu2/gI2hrm/pR+3dIb9N7k1wqCA72TCf8KLnn46Yi/y6AWt9jfm6TpdkUzZYBuPCTNB8inCfpPiqKcc32kHZcnwAa6wDsmupUgG1fjgF2TM0BDV9EboEPU++ALRERF+DI0GrvdVXg3lDgPcxOgTNd3aiQDPCp66ietnQdA17CdASo65oBSt3KgY6uU8BydMDm7+l4pI4qbtAz51QLwnq/4t2oR8W96Tqh97lkTKGszaho6KGa7wgQ+IZO9u9G3dwNl769ulTajfB1aidvYpqJHloSU+dZ5cFjXGW00s3XJEHlJOQVSVRZeKgEGwd//1mZAgrd3FstA83ro0RVx28UbErHtymLVm0na8fznntXP6Y1kYlN4CpNZV5d/UR1gddUdfSrZ6hY1f7bbHQbEzRjO7PT+9TFtCX5mfRe2fs8yIwatvkGRctudPbS9qrtedMpO2n87Q31RzqMv45vnEv/wxKJ/6wAAAAASUVORK5CYII=');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `cart_code` text CHARACTER SET armscii8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `cart_code`) VALUES
(1, 'root', '$2a$10$4VgZALEfgeqIIjEDcdHunuvRqp50c4vOIc54QZMeqHXkQmFjQi5ue', '-6A2-');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
